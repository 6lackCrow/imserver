package xyz.crowxx.imserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import xyz.crowxx.imserver.model.Token;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Token findTokenByToken(String token);
}
