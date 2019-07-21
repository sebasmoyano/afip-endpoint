package io.slingr.endpoints.afip.mgdtrat.util;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.util.Properties;

/**
 * Lee y escribe en el archivo de configuración.
 * En primer lugar intenta leer el archivo de configuración temporal, si este no
 * existe (porque todavía no se ha creado), entonces lee el archivo de configuración
 * origianl (de solo lectura).
 * La primera vez que se ejecuta, lee el archivo de configuración (
 * configuracion.properties de solo lectura).
 * Cuando se genera el token/sign de AFIP, se gaurda en el archivo de configuración
 * temporal (en disco) ya que si el token está creado y todavía no ha vencido,
 * AFIP no me permite solictar otro ticket de acceso.
 * El archivo de configuración temporal se crea la primera vez que se setea alguna
 * propiedad, y posteriormente se va actualizando cada vez que se modifica un propiedad.
 *
 * @author itraverso
 */
public class GestorDeConfiguracion {

    private static final Logger logger = LoggerFactory.getLogger(GestorDeConfiguracion.class);

    private static GestorDeConfiguracion instance = null;

    private Properties config;

    public final static String PATH_CONFIG_DIR = System.getProperty("user.home") + "/afip/config/";
    private final static String CONFIG_FILE = "configuracion.properties";
    public final static String CERT_FILE = "certificado.p12";

    private GestorDeConfiguracion() {
        try {
            String configPath = PATH_CONFIG_DIR + CONFIG_FILE;
            InputStream resourcesConfigFile = getClass().getClassLoader().getResourceAsStream(CONFIG_FILE);
            FileUtils.copyInputStreamToFile(resourcesConfigFile, new File(configPath));
            InputStream archivoConfiguracion = new FileInputStream(configPath);
            this.config = new Properties();
            this.config.load(archivoConfiguracion);
        } catch (Exception e) {
            logger.error("No se pudo inicializar la configuracion", e);
            throw new RuntimeException("No se puede inicializar la configuracion");
        }
    }

    public static GestorDeConfiguracion getInstance() {
        if (instance == null) {
            instance = new GestorDeConfiguracion();
        }

        return instance;
    }

    public String getProperty(String key) {
        return this.config.getProperty(key);
    }

    public String getProperty(String key, String defaultValue) {
        return this.config.getProperty(key, defaultValue);
    }

    public void setProperty(String key, String value) {
        this.config.setProperty(key, value);

        //Cada vez que seteo una propiedad, la guardo en el archivo de configuración.
        try (OutputStream archivoConfiguracion = new FileOutputStream(new File(this.PATH_CONFIG_DIR + this.CONFIG_FILE))) {
            this.config.store(archivoConfiguracion, "Archivo de configuración TEMPORAL");
            archivoConfiguracion.flush();

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Retorna el path absoluto al directorio de configuración.
     *
     * @return
     */
    public String getAbsolutePathConfigurationDir() {
        return this.PATH_CONFIG_DIR;
    }
}
