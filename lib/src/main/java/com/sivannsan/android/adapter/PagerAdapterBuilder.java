package com.sivannsan.android.adapter;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.sivannsan.foundation.annotation.Nonnull;

import java.util.List;
import java.util.function.Supplier;

public class PagerAdapterBuilder {
    @Nonnull
    private final List<? extends View> pages;

    public PagerAdapterBuilder(List<? extends View> pages) {
        this.pages = pages;
    }

    public PagerAdapterBuilder(Supplier<List<? extends View>> pagesGetter) {
        this.pages = pagesGetter.get();
    }

    @Nonnull
    public PagerAdapter build() {
        return new PagerAdapter() {
            @Override
            public int getCount() {
                return pages.size();
            }

            @Override
            public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
                return view == object;
            }

            @NonNull
            @Override
            public Object instantiateItem(@NonNull ViewGroup container, int position) {
                container.addView(pages.get(position));
                return pages.get(position);
            }

            @Override
            public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
                container.removeView(pages.get(position));
            }
        };
    }
}
