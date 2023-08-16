package likelion.univ.oauth.kakao.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;

@Getter
@AllArgsConstructor
@ConfigurationProperties(prefix = "kakao")
@ConstructorBinding
public class KakaoProperties {
    private String clientId;
    private String clientSecret;
    private String redirectUrl;
    private String appId;
}
