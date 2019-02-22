package com.wp.demo.controller;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wp.demo.bean.Commodity;
import com.wp.demo.bean.Department;
import com.wp.demo.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.*;

/**
 * Created by Administrator on 2019/1/2.
 */
@Controller
public class ShoppingController {

    @Autowired
    ProductService productService;

    //@ResponseBody
    @GetMapping("/shopping/goshopping")
    public String shoppingFirstPage(Model model) throws Exception {

        List<Commodity> all = productService.findAll();
        model.addAttribute("all",all);

        return "/shopping/myshopping";
    }

    //分页显示商品
    @GetMapping("/shopping/goshoppingbypage")
    public String getCommoditiesByPage(@RequestParam(defaultValue = "1") int pageNo,
                                       @RequestParam(defaultValue = "6") int pageSize,
                                       Model model) throws Exception {
        PageHelper.startPage(pageNo, pageSize);
        Page<Commodity> all = productService.findAll();

        //使用pageInfo包装查询后的结果，只需要将pageInfo交给页面就行了
        //封装了详细的分页信息，包括我们查询出来的数据,传入连续显示的页数eg:5  表示连续显示5页
        PageInfo<Commodity> page = new PageInfo<>(all,5);
        model.addAttribute("pageInfo",page);

        return "/shopping/myshopping";
    }


    /**
     * 处理添加到购物车的ajax请求
     * @param pid
     * @return
     */
    @ResponseBody
    @RequestMapping(value = "/usr/ajax/addToShoppingCar")
    public String addToShoppingCar(@RequestParam(value = "pid") Integer pid, HttpSession session){

        Map<Integer,Integer> cars = (Map<Integer,Integer>) session.getAttribute("allpid");

        if(cars == null){
            cars = new HashMap<>();                     //如果是第一次添加到购物车
        }
        if(cars.get(pid) == null){                     //若购物车中没有改商品,则向购物车中添加该商品
            cars.put(pid,1);
        }else {
            int val = cars.get(pid);
            val++;
            cars.put(pid,val);
        }

        session.setAttribute("allpid",cars);        //将所有购买的商品的pid保存到该用户对应的session域中

        return "true";
    }


    /**
     * 购物车界面，该界面可以查看购买了哪些商品，每件商品的数量
     * @param session
     * @param model
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/usr/toOrderCar",method = RequestMethod.GET)
    public String toOrderCar(HttpSession session,Model model) throws Exception {

        HashMap<Integer,Integer> allpid = (HashMap<Integer,Integer>)session.getAttribute("allpid");
        Map<Integer,Commodity> commodities = new HashMap();

        if(allpid != null){
            Set<Integer> keys = allpid.keySet();
            Iterator<Integer> iterator = keys.iterator();
            int sumCount = 0;           //总共购买的商品数量
            double sumPrice = 0;        //总共价钱
            int pid = 0;
            int commodityCount = 0;     //每件商品的数量
            Map<Integer,Integer> cars = new HashMap<>();

            while (iterator.hasNext()){
                pid = iterator.next();
                commodityCount = allpid.get(pid);
                //根据加入购物车的商品的pid从数据库查询出商品
                Commodity commodity = productService.findById(pid);

                //计算商品的总价钱
                sumPrice = sumPrice + commodity.getPrice() * commodityCount;

                //将商品的pid和对应的购买数量作为键值对，方便结算页面取得商品数量
                cars.put(commodity.getPid(),commodityCount);
                //以购买每件商品的pid作为key，商品作为value
                commodities.put(commodity.getPid(),commodity);
                sumCount++;
            }

            model.addAttribute("cars",cars);
            model.addAttribute("allCommotity",commodities);
            model.addAttribute("sumCount",sumCount);
            model.addAttribute("sumPrice",sumPrice);
        }
        return "/shopping/myShoppingCar";
    }

    /**
     * 由购物车中勾选好商品后
     * @param checkboxpid
     * @param model
     * @return
     */
    @RequestMapping(value = "/usr/ajax/toMyOrder/{checkboxpid}/{commodityCount}" ,method = RequestMethod.GET)
    public String myOder(@PathVariable("checkboxpid") String checkboxpid,@PathVariable("commodityCount")
            String commodityCount, Model model,HttpSession session) throws Exception {

        buyCommodityUtil(model,checkboxpid,commodityCount);

        //跳转去我的订单界面，然后在session中清空选中的商品
       if(!checkboxpid.equals("||")){
           Map<Integer,Integer> cars = (Map<Integer,Integer>) session.getAttribute("allpid");
           String[] pids = checkboxpid.split("--");
           for (int i = 0; i < pids.length; i++) {
               cars.remove(Integer.parseInt(pids[i]));
           }

           System.out.println("pids: " + checkboxpid + "cars的长度： " + cars.size());
           //将修改后的pids放回session中
           session.setAttribute("allpid",cars);
       }

        return "/shopping/myOrder";
    }

    //处理直接从侧边栏点击去我的订单
    @RequestMapping(value = "/usr/ajax/toMyOrder",method = RequestMethod.GET)
    public String toMyOrder(Model model) throws Exception {

        Map<Integer,Commodity> commodityMap = new HashMap<>();
        model.addAttribute("commodityMap",commodityMap);

        return "/shopping/myOrder";
    }

    /**
     * 处理在二手界面直接点击购买请求
     * @param pid
     * @return
     */
    @RequestMapping(value = "/usr/ajax/buyImmediately/{pid}",method = RequestMethod.GET)
    public String buyImmediately(@PathVariable("pid") Integer pid,Model model) throws Exception {

        buyCommodityUtil(model,pid+"");

        return "/shopping/myOrder";
    }

    /**
     * 处理立即购买和从购物车选择再购买，将它抽取为一个方法
     * @param model
     * @param args
     * @return
     * @throws Exception
     */
    public void buyCommodityUtil(Model model,String...args) throws Exception {

        int sumCount = 0;           //总共购买的商品数量
        double sumPrice = 0;        //总共价钱
        Map<Integer,Commodity> commodityMap = new HashMap<>();
        Map<Integer,Integer> counts = new HashMap<>();

        //处理立即购买
        if(args.length == 1){
            Commodity commodity = productService.findById(Integer.parseInt(args[0]));
            if (commodity != null) {
                commodityMap.put(commodity.getPid(), commodity);
                counts.put(commodity.getPid(), 1);
                sumPrice = commodity.getPrice();
                sumCount = 1;
            }
        }
        //处理从购物车中进行购买,第一个参数为pid的拼串，第二个参数为数量的拼串
        if(args.length == 2){
            if(!args[0].equals("||")) {

                String[] pids = args[0].split("--");
                String[] commodityCounts = args[1].split("__");

                for (int i = 0; i < pids.length; i++) {
                    try {
                        Commodity commodity = productService.findById(Integer.parseInt(pids[i]));
                        //判断是否在数据库中查询到了该商品
                        if (commodity != null) {
                            commodityMap.put(commodity.getPid(), commodity);
                            //保存商品pid 和 购买的数量 作为key-value键值对,在前台通过pid来取得商品的购买数量
                            counts.put(commodity.getPid(), Integer.parseInt(commodityCounts[i]));

                            sumPrice = sumPrice + commodity.getPrice() * Integer.parseInt(commodityCounts[i]);
                            sumCount++;
                        }
                    } catch (Exception e) {
                        throw new Exception("数据转换异常！！！");
                    }
                }
            }
        }
        model.addAttribute("commodityMap",commodityMap);
        model.addAttribute("counts",counts);
        model.addAttribute("sumCount",sumCount);
        model.addAttribute("sumPrice",sumPrice);
    }

}
