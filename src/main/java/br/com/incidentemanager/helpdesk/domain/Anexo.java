package br.com.incidentemanager.helpdesk.domain;

import lombok.Data;

@Data
public class Anexo {

    private String filename;

    private String content;

    public String getFilename() {
        return filename;
    }

    public String getContent() {
        return content;
    }
}
