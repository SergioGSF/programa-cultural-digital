package br.com.projeto.arenapernambuco.service;

import io.github.bucket4j.Bandwidth;
import io.github.bucket4j.Bucket;
import io.github.bucket4j.Refill;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class RateLimitService {
    
    private final Map<String, Bucket> cache = new ConcurrentHashMap<>();
    
    public Bucket resolveBucket(String identificador) {
        return cache.computeIfAbsent(identificador, k -> criarNovoBucket());
    }
    
    private Bucket criarNovoBucket() {
        // 5 tentativas a cada 15 minutos para login
        Bandwidth limit = Bandwidth.builder()
        .capacity(5)
        .refillIntervally(5, Duration.ofMinutes(15))
        .build();
        return Bucket.builder()
                .addLimit(limit)
                .build();
    }
    
    public boolean permitirRequisicao(String identificador) {
        Bucket bucket = resolveBucket(identificador);
        return bucket.tryConsume(1);
    }
}
