package io.slingr.endpoints.afip.mgdtrat.wsafip;

import java.util.Date;

public class TicketAccesoAFIP {

    private String token;

    private String sign;

    private Date expiracionTA;

    public TicketAccesoAFIP() {
        super();
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public Date getExpiracionTA() {
        return expiracionTA;
    }

    public void setExpiracionTA(Date expiracionTA) {
        this.expiracionTA = expiracionTA;
    }

    /**
     * Retorna true si el TA (Ticket de acceso) está activo,
     * falso en caso contrario.
     *
     * @return
     */
    public boolean estaActivoTAAFIP() {
        Date now = new Date();
        if (getExpiracionTA() == null || now.compareTo(getExpiracionTA()) > 0) {
            return false;
        }
        return true;
    }
}
