package com.jess.arms.utils.logger;

public interface LogStrategy {

  void log(int priority, String tag, String message);
}
