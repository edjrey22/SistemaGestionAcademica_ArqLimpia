package com.academico.infrastructure.config;

/**
 * Singleton: única fuente de configuración de la aplicación.
 * Lee las variables de entorno una sola vez y las comparte con todas las capas.
 */
public final class ConfiguracionApp {

    // volatile: todos los hilos ven la instancia ya construida
    private static volatile ConfiguracionApp instancia;

    private final String tipoAlmacenamiento;
    private final String dbUrl;
    private final String dbUsuario;
    private final String dbPassword;

    // Constructor privado: nadie fuera de la clase puede usar "new"
    private ConfiguracionApp() {
        this.tipoAlmacenamiento = System.getenv()
                .getOrDefault("STORAGE_TYPE", "JSON").trim().toUpperCase();
        this.dbUrl = System.getenv().getOrDefault("DB_URL",
                "jdbc:postgresql://localhost:5432/gestion_academica");
        this.dbUsuario = System.getenv().getOrDefault("DB_USER", "postgres");
        this.dbPassword = System.getenv("DB_PASSWORD");
        System.out.println("[SINGLETON] ConfiguracionApp creada (almacenamiento = "
                + tipoAlmacenamiento + ")");
    }

    // Doble verificación con bloqueo: segura cuando varios hilos la piden a la vez
    public static ConfiguracionApp getInstancia() {
        if (instancia == null) {
            synchronized (ConfiguracionApp.class) {
                if (instancia == null) {
                    instancia = new ConfiguracionApp();
                }
            }
        }
        return instancia;
    }

    public String getTipoAlmacenamiento() { return tipoAlmacenamiento; }
    public String getDbUrl()              { return dbUrl; }
    public String getDbUsuario()          { return dbUsuario; }
    public String getDbPassword()         { return dbPassword; }
}
