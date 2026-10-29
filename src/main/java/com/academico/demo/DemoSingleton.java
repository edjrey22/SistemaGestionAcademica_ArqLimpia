package com.academico.demo;

import com.academico.infrastructure.config.ConfiguracionApp;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/** Demostración: varios hilos piden la configuración al mismo tiempo. */
public class DemoSingleton {

    public static void main(String[] args) throws InterruptedException {
        System.out.println("=== DEMO SINGLETON: 5 hilos piden la configuración ===");
        Set<Integer> instancias = ConcurrentHashMap.newKeySet();
        List<Thread> hilos = new ArrayList<>();

        for (int i = 1; i <= 5; i++) {
            int n = i;
            Thread hilo = new Thread(() -> {
                ConfiguracionApp config = ConfiguracionApp.getInstancia();
                int id = System.identityHashCode(config);
                instancias.add(id);
                System.out.println("Hilo " + n + " -> instancia @"
                        + Integer.toHexString(id));
            });
            hilos.add(hilo);
            hilo.start();
        }
        for (Thread h : hilos) {
            h.join();
        }
        System.out.println("Instancias distintas creadas: " + instancias.size());
    }
}
