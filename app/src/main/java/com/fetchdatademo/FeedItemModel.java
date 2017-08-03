package com.fetchdatademo;

/**
 * Created by SATYAPRAKASH on 02-08-2017.
 */

public class FeedItemModel {
    private String title;
    private String link;
    private String description;
    private String pubData;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPubData() {
        return pubData;
    }

    public void setPubData(String pubData) {
        this.pubData = pubData;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    public void setThumbnailUrl(String thumbnailUrl) {
        this.thumbnailUrl = thumbnailUrl;
    }

    private String thumbnailUrl;

}
