package br.com.bb.cotacao.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TracerConfig {

    @Bean
    public io.opentracing.Tracer getTracer() {
        io.jaegertracing.Configuration.SamplerConfiguration samplerConfig = io.jaegertracing.Configuration
                .SamplerConfiguration.fromEnv()
                .withType("const").withParam(1);
        io.jaegertracing.Configuration.ReporterConfiguration reporterConfig = io.jaegertracing.Configuration
                .ReporterConfiguration.fromEnv()
                .withLogSpans(true);
        io.jaegertracing.Configuration config = new io.jaegertracing.Configuration("math-service")
                .withSampler(samplerConfig)
                .withReporter(reporterConfig);
        return config.getTracer();
    }
}