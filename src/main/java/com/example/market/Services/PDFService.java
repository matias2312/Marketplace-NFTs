
package com.example.market.Services;

import com.example.market.models.Client;
import com.example.market.models.ClientProduct;


import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Set;

public interface PDFService {
    ByteArrayOutputStream generatePDF(HttpServletResponse response, Client client, Set<ClientProduct> sales) throws IOException;
}