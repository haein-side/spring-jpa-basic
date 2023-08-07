package jpabook.jpashop.domain;

import javax.persistence.Entity;

@Entity
public class Album extends Item {

    private String artists;
    private String etc;

    public String getArtists() {
        return artists;
    }

    public void setArtists(String artists) {
        this.artists = artists;
    }

    public String getEtc() {
        return etc;
    }

    public void setEtc(String etc) {
        this.etc = etc;
    }
}
