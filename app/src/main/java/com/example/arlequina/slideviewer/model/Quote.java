package com.example.arlequina.slideviewer.model;

/**
 * Created by ARLEQUINA on 12/28/2017.
 */

public class Quote {

    private String q_text;
    private String q_auth;

    public Quote(String t, String a)
    {
        this.q_text = t;
        this.q_auth = a;
    }
    public String getQtext()
    {
        return q_text;
    }
    public String getQauth()
    {
        return q_auth;
    }
    public void setQtext(String t)
    {
        this.q_text = t;
    }
    public void setQauth(String a)
    {
        this.q_auth = a;
    }
}
