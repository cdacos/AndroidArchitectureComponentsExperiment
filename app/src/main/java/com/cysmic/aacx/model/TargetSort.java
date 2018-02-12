package com.cysmic.aacx.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.inject.Inject;

public class TargetSort {
  public enum SortBy {
    NAME,
    RESULT
  }

  @Inject
  public TargetSort() { }

  private SortBy currentSortBy = SortBy.NAME;

  private static final Comparator<Target> nameComparator = new Comparator<Target>() {
    @Override
    public int compare(Target o1, Target o2) {
      if (o1 != null && o2 != null && o1.getName() != null)
        return o1.getName().compareToIgnoreCase(o2.getName());
      return 0;
    }
  };

  private static final Comparator<Target> resultComparator = new Comparator<Target>() {
    @Override
    public int compare(Target o1, Target o2) {
      if (o1 != null && o2 != null) {
        float i1 = o1.getResult() > 0 ? o1.getResult() : Integer.MAX_VALUE;
        float i2 = o2.getResult() > 0 ? o2.getResult() : Integer.MAX_VALUE;
        return Float.compare(i1, i2);
      }
      return 0;
    }
  };

  List<Target> sortData(List<Target> list, SortBy sortBy) {
    currentSortBy = sortBy;
    return sortData(list);
  }

  List<Target> sortData(List<Target> list) {
    if (list != null) {
      list = new ArrayList<>(list);
      if (currentSortBy.equals(SortBy.RESULT)) {
        Collections.sort(list, resultComparator);
      }
      else {
        Collections.sort(list, nameComparator);
      }
    }
    return list;
  }
}
