/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package java.net;

import java.io.IOException;

/**
 *
 * @author asus
 */
public class MalformedURLException extends IOException {
    private static final long serialVersionUID = -182787522200415866L;

    /**
     * Constructs a {@code MalformedURLException} with no detail message.
     */
    public MalformedURLException() {
    }

    /**
     * Constructs a {@code MalformedURLException} with the
     * specified detail message.
     *
     * @param   msg   the detail message.
     */
    public MalformedURLException(String msg) {
        super(msg);
    }
}

