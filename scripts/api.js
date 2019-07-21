

/**
 * Obtiene una nueva autorizacion desde AFIP para los siguientes tipos de comprobantes:
 *             ID: 1 - Factura A
 *             ID: 2 - Nota de Débito A
 *             ID: 3 - Nota de Crédito A
 *             ID: 6 - Factura B
 *             ID: 7 - Nota de Débito B
 *             ID: 8 - Nota de Crédito B
 */
endpoint.autorizarComprobante = function (datosComprobante) {
    return endpoint._autorizarComprobante(datosComprobante);
};