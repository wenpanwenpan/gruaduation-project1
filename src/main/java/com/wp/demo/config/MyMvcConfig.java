package com.wp.demo.config;

import com.wp.demo.componpent.MyLocaleResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.lang.Nullable;
import org.springframework.validation.MessageCodesResolver;
import org.springframework.validation.Validator;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.util.List;

/**
 * Created by Administrator on 2018/12/20.
 * 该类主要作用就是扩展springmvc配置，以前在springmvc中编写的springmvc.xml配置文件中的
 * 所有配置，都可以在该类中进行配置。在该类中覆写对应的方法，就实现了对相应的配置
 * WebMvcConfigurerAdapter：该类可以扩展springmvc配置
 * @Configuration:表示该类是一个配置类，要加入到容器中
 */
@Configuration
public class MyMvcConfig extends WebMvcConfigurerAdapter {
    public MyMvcConfig() {
        super();
    }

    /**addViewControllers方法：
     * 拓展mvc功能，以前在配置文件中配置的现在在配置类中实现，当用户发/wenpan 请求时，跳转到hello.html页面
     * @param registry
     */
    /*@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        //super.addViewControllers(registry);
        registry.addViewController("/wenpan").setViewName("hello");
    }*/


    //第二种方法：所有的WebMvcConfigurerAdapter都会一起作用
    @Bean
    public WebMvcConfigurerAdapter webMvcConfigurerAdapter(){

        WebMvcConfigurerAdapter adapter = new WebMvcConfigurerAdapter(){
            /**
             * thymeleaf中的每一个带有静态资源的请求都必须要经过视图解析器的映射吗？？？？
             * @param registry
             */
            @Override
            public void addViewControllers(ViewControllerRegistry registry) {
                //设置根目录下的一些访问默认跳转到首页
                registry.addViewController("/index.html").setViewName("login");
                registry.addViewController("/").setViewName("login");
                //为main.html请求添加视图映射
                registry.addViewController("/main.html").setViewName("dashboard");
            }


            /**
             * springboot已经做好了静态资源映射，所以这里不拦截静态资源（*.css,*.js）
             * 下面设置要拦截哪些请求，和不拦截哪些请求
             * @param registry
             */
            @Override
            public void addInterceptors(InterceptorRegistry registry) {

              //  registry.addInterceptor(new LoginHandlerInterceptor()).addPathPatterns("/**")
                //        .excludePathPatterns("/index.html","/","/usr/login");
            }
        };



        return adapter;
    }

    //将国际化信息类加载到容器中
    @Bean
    public LocaleResolver localeResolver(){
        return new MyLocaleResolver();
    }


    @Override
    public void configurePathMatch(PathMatchConfigurer configurer) {
        super.configurePathMatch(configurer);
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        super.configureContentNegotiation(configurer);
    }

    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        super.configureAsyncSupport(configurer);
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        super.configureDefaultServletHandling(configurer);
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        super.addFormatters(registry);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        super.addInterceptors(registry);
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        super.addResourceHandlers(registry);
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        super.addCorsMappings(registry);
    }


    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        super.configureViewResolvers(registry);
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        super.addArgumentResolvers(argumentResolvers);
    }

    @Override
    public void addReturnValueHandlers(List<HandlerMethodReturnValueHandler> returnValueHandlers) {
        super.addReturnValueHandlers(returnValueHandlers);
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.configureMessageConverters(converters);
    }

    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        super.extendMessageConverters(converters);
    }

    @Override
    public void configureHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        super.configureHandlerExceptionResolvers(exceptionResolvers);
    }

    @Override
    public void extendHandlerExceptionResolvers(List<HandlerExceptionResolver> exceptionResolvers) {
        super.extendHandlerExceptionResolvers(exceptionResolvers);
    }

    @Nullable
    @Override
    public Validator getValidator() {
        return super.getValidator();
    }

    @Nullable
    @Override
    public MessageCodesResolver getMessageCodesResolver() {
        return super.getMessageCodesResolver();
    }
}
