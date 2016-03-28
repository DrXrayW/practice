package xray.leetcode.array.skyline;

import util.Ref;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class DrawingSkylines_merge {
  // @include
  
  /*
   * given a list of skylines: with left, right and height (not coordinates, but pixel positions)
   * find the top of everything
   */
  public static List<Skyline> drawingSkylines(List<Skyline> skylines) {
    return drawingSkylinesHelper(skylines, 0, skylines.size());
  }

  private static List<Skyline> drawingSkylinesHelper(List<Skyline> skylines,
                                                     int start, int end) {
    if (end - start <= 1) { // 0 or 1 skyline, just copy it.
      return new ArrayList<>(skylines.subList(start, end));
    }
    int mid = start + ((end - start) / 2);
    List<Skyline> L = drawingSkylinesHelper(skylines, start, mid);
    List<Skyline> R = drawingSkylinesHelper(skylines, mid, end);
    return mergeSkylines(L, R);
  }

  private static List<Skyline> mergeSkylines(List<Skyline> L, List<Skyline> R) {
    int i = 0, j = 0;
    List<Skyline> merged = new ArrayList<>();

    while (i < L.size() && j < R.size()) {
      if (L.get(i).right < R.get(j).left) {
        merged.add(L.get(i++));
      } else if (R.get(j).right < L.get(i).left) {
        merged.add(R.get(j++));
      } else if (L.get(i).left <= R.get(j).left) {
        Ref<Integer> iWrapper = new Ref<>(i);
        Ref<Integer> jWrapper = new Ref<>(j);
        mergeIntersectSkylines(merged, L.get(i), iWrapper, R.get(j), jWrapper);
        i = iWrapper.value;
        j = jWrapper.value;
      } else { // L.get(i).left > R.get(j).left.
        Ref<Integer> iWrapper = new Ref<>(i);
        Ref<Integer> jWrapper = new Ref<>(j);
        mergeIntersectSkylines(merged, R.get(j), jWrapper, L.get(i), iWrapper);
        i = iWrapper.value;
        j = jWrapper.value;
      }
    }
    merged.addAll(L.subList(i, L.size()));
    merged.addAll(R.subList(j, R.size()));
    return merged;
  }

  private static void mergeIntersectSkylines(List<Skyline> merged, Skyline a,
                                             Ref<Integer> aIdx, Skyline b,
                                             Ref<Integer> bIdx) {
    if (a.right <= b.right) {
      if (a.height > b.height) {
        if (b.right != a.right) {
          merged.add(a);
          aIdx.value = aIdx.value + 1;
          b.left = a.right;
        } else {
          bIdx.value = bIdx.value + 1;
        }
      } else if (a.height == b.height) {
        b.left = a.left;
        aIdx.value = aIdx.value + 1;
      } else { // a->height < b->height.
        if (a.left != b.left) {
          merged.add(new Skyline(a.left, b.left, a.height));
        }
        aIdx.value = aIdx.value + 1;
      }
    } else { // a.right > b.right.
      if (a.height >= b.height) {
        bIdx.value = bIdx.value + 1;
      } else {
        if (a.left != b.left) {
          merged.add(new Skyline(a.left, b.left, a.height));
        }
        a.left = b.right;
        merged.add(b);
        bIdx.value = bIdx.value + 1;
      }
    }
  }
  // @exclude

  public static void main(String[] args) {
    Random r = new Random();
    for (int times = 0; times < 2000; ++times) {
      int n;
      if (args.length == 1) {
        n = Integer.parseInt(args[0]);
      } else {
        //n = r.nextInt(5000) + 1;
    	  n = 3;
      }
      List<Skyline> A = new ArrayList<>();
      for (int i = 0; i < n; ++i) {
        int left = r.nextInt(3);
        int right = r.nextInt(5) + left;
        int height = r.nextInt(3);
        A.add(new Skyline(left, right, height));
      }
      List<Skyline> ans = drawingSkylines(A);
      System.out.println("n = " + n);
      // Just check there is no overlap.
      for (int i = 0; i < ans.size(); ++i) {
        assert (ans.get(i).left <= ans.get(i).right);
        if (i > 0) {
          assert (ans.get(i - 1).right <= ans.get(i).left);
          assert (ans.get(i - 1).right != ans.get(i).left || ans.get(i - 1).height != ans
              .get(i).height);
        }
      }
    }
  }
}