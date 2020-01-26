
package br.com.victoramaral.githubdive.model.pojos.requests;

import com.google.gson.annotations.Expose;

public class Comments {

    @Expose
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
