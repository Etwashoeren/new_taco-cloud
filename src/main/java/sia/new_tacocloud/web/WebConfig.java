package sia.new_tacocloud.web;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer { // 뷰 컨트롤러의 역할을 수행하는 구성 클래스

    /*
    HomeController와 같이 모델 데이터나 사용자 입력을 처리하지 않느 간단한 컨트롤러의 경우 다른 방법으로 컨트롤러를 정의 가능.
    이 클래스는 뷰에 요청을 전달하는 일만 하는 컨트롤러이다.
    */

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
    }
}
