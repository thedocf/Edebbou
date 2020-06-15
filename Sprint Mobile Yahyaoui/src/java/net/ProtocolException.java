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
public
class ProtocolException extends IOException {
    private static final long serialVersionUID = -6098449442062388080L;

    /**
     * Constructs a new {@code ProtocolException} with the
     * specified detail message.
     *
     * @param   host   the detail message.
     */
    public ProtocolException(String host) {
        super(host);
    }

    /**
     * Constructs a new {@code ProtocolException} with no detail message.
     */
    public ProtocolException() {
    }
}
