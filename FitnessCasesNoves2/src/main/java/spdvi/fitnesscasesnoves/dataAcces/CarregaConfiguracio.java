/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spdvi.fitnesscasesnoves.dataAcces;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CarregaConfiguracio {

    // Mètode per carregar el fitxer de configuració
    public Properties carregarConfig(String filePath) {
        Properties properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(filePath)) {
            if (input == null) {
                Logger.getLogger(CarregaConfiguracio.class.getName()).log(Level.SEVERE, "No s'ha trobat el fitxer de configuració");
                return null;
            }
            properties.load(input);
        } catch (IOException ex) {
            Logger.getLogger(CarregaConfiguracio.class.getName()).log(Level.SEVERE, "Error carregant el fitxer de configuració", ex);
        }
        return properties;
    }
}

