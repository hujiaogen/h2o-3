package hex.deepwater;

import water.util.Log;

import java.io.IOException;

public class MXNetLoader {
  static { //only load libraries once
    try {
      final boolean GPU = System.getenv("CUDA_PATH")!=null;
      if (GPU) {
        Log.info("Loading CUDA library.");
        water.gpu.util.loadCudaLib();
        hex.deepwater.DeepWater.logNvidiaStats();
      }
      Log.info("Loading mxnet library.");
      water.gpu.util.loadNativeLib("mxnet");
      Log.info("Loading H2O mxnet bindings.");
      water.gpu.util.loadNativeLib("Native");
    } catch (IOException e) {
      e.printStackTrace();
      throw new IllegalArgumentException("Couldn't load native libraries");
    }
  }
}