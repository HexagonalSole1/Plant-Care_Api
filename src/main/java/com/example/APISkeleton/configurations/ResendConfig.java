package com.example.APISkeleton.configurations;


import com.resend.Resend;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ResendConfig {

    @Bean
    public Resend resendClient() {
        return new Resend("re_Dzf59npW_8ZaS2UShsdevX8b2aowybTdd");  // Reemplaza "YOUR_API_KEY" con tu clave de API de Resend
    }
}
