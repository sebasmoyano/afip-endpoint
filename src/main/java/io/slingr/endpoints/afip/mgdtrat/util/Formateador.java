/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.slingr.endpoints.afip.mgdtrat.util;

import org.apache.commons.lang3.StringUtils;

/**
 *
 * @author itraverso
 */
public class Formateador {

    public static String leftPad(String inputString, int size, String padStr) {
        return StringUtils.leftPad(inputString, size, padStr);
    }

    public static String leftPadWithCeros(String inputString, int size) {
        return Formateador.leftPad(inputString, size, "0");
    }
}
