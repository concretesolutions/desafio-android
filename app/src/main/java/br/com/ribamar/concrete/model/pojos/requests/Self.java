
package br.com.ribamar.concrete.model.pojos.requests;

import com.google.gson.annotations.Expose;

public class Self {

    @Expose
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
