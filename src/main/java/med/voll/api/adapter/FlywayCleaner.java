package med.voll.api.adapter;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Autowired;

public class FlywayCleaner {

    private final Flyway flyway;

    @Autowired
    public FlywayCleaner(Flyway flyway) {
        this.flyway = flyway;
    }

    public void limparBanco() {
        flyway.clean();
        flyway.migrate();
    }
}
