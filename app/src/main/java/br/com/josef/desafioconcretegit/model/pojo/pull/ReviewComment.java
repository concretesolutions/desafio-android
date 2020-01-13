
package br.com.josef.desafioconcretegit.model.pojo.pull;


import com.google.gson.annotations.Expose;

public class ReviewComment {

    @Expose
    private String href;

    public String getHref() {
        return href;
    }

    public void setHref(String href) {
        this.href = href;
    }

}
