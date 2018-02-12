package com.cysmic.aacx.model;

import com.google.gson.annotations.SerializedName;

@SuppressWarnings ("unused")
public class Target {
  @SerializedName("name")
  private String name;
  @SerializedName("url")
  private String url;
  @SerializedName("icon")
  private String iconUrl;

  private float result;
  private String error;

  public Target() { }

  public Target(String name, String url, String iconUrl) {
    this.name = name;
    this.url = url;
    this.iconUrl = iconUrl;
  }

  public float getResult() {
    return result;
  }

  public void setResult(float result) {
    this.result = result;
  }

  public String getError() {
    return error;
  }

  void setError(String error) {
    this.error = error;
  }

  public String getName() {
    return name;
  }

  public String getUrl() {
    return url;
  }

  public String getIconUrl() {
    return iconUrl;
  }
}
