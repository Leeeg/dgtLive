package com.dgtis.live.widget.wheelview;

import android.content.Context;

import com.dgtis.live.myapplication.R;

import java.util.Arrays;
import java.util.List;

public class ProvinceWheelAdapter implements WheelAdapter {
	
	private List<String> provinces;
	
	public ProvinceWheelAdapter(Context context){
		provinces = Arrays.asList(context.getResources().getStringArray(R.array.province_item));
	}

	@Override
	public int getItemsCount() {
		return provinces == null ? 0 : provinces.size();
	}

	@Override
	public String getItem(int index) {
		return index <= provinces.size() - 1 ? provinces.get(index) : null;
	}

	@Override
	public int getMaximumLength() {
		return 7;
	}
	
	@Override
	public String getCurrentId(int index) {
		return "";
	}

}
