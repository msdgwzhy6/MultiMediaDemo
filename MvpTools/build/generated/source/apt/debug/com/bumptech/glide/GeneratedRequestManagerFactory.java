package com.bumptech.glide;

import com.bumptech.glide.manager.Lifecycle;
import com.bumptech.glide.manager.RequestManagerRetriever;
import com.bumptech.glide.manager.RequestManagerTreeNode;
import com.jess.arms.http.imageloader.glide.GlideRequests;
import java.lang.Override;

/**
 * Generated code, do not modify
 */
final class GeneratedRequestManagerFactory implements RequestManagerRetriever.RequestManagerFactory {
  @Override
  public RequestManager build(Glide glide, Lifecycle lifecycle, RequestManagerTreeNode treeNode) {
    return new GlideRequests(glide, lifecycle, treeNode);
  }
}
