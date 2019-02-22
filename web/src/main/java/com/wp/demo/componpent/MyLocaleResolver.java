package com.wp.demo.componpent;

import org.springframework.lang.Nullable;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.LocaleResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

/**
 * Created by Administrator on 2018/12/20.
 * 该类用于实现国际化语言的切换，区域信息解析器
 */
public class MyLocaleResolver implements LocaleResolver{

    /**
     * 解析国际化信息：
     * 区域信息解析器其实是根据请求头带的信息，来决定使用那个语言的
     * 可以自己定义一个区域信息解析器类（需要实现LocaleResolver类，然后将自定义的解析器注册到容器中），
     * 来获取locale信息，来改变语言信息实现国际化
     * @param request
     * @return
     */
    @Override
    public Locale resolveLocale(HttpServletRequest request) {
        //取得请求中传来的参数（获取链接上携带的国际化信息）
        String l = request.getParameter("l");
        Locale locale = Locale.getDefault();
        if(!StringUtils.isEmpty(l)){     //如果有带l参数,如果没带就用系统默认的
            String[] split = l.split("_");
            //将国际化信息设置到locale中
            locale = new Locale(split[0], split[1]);
        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest httpServletRequest, @Nullable HttpServletResponse httpServletResponse, @Nullable Locale locale) {

    }
}
