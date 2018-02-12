package com.cysmic.aacx.net;

import com.cysmic.aacx.model.Target;

import java.util.List;

public interface FetchResponse {
  void onFetchResponse(List<Target> list, String message);
}
