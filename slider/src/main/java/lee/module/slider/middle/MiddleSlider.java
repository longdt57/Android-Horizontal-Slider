/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package lee.module.slider.middle;

import static java.util.Collections.max;
import static java.util.Collections.min;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;

import androidx.annotation.DrawableRes;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.R;

import java.util.Arrays;
import java.util.List;

import lee.module.slider.material.BaseOnChangeListener;
import lee.module.slider.material.BaseOnSliderTouchListener;

/**
 * A widget that allows picking a value within a given range by sliding a thumb along a horizontal
 * line.
 *
 * <p>{@inheritDoc}
 *
 * <p>{@code android:value}: <b>Optional.</b> The initial value of the slider. If not specified, the
 * slider's minimum value {@code android:valueFrom} is used.
 *
 * @attr ref com.google.android.material.R.styleable#SingleSlider_android_value
 */
public class MiddleSlider extends BaseMiddleSlider<MiddleSlider, MiddleSlider.OnChangeListener, MiddleSlider.OnSliderTouchListener> {

  public float valueActiveFixed;

  /** Interface definition for a callback invoked when a slider's value is changed. */
  public interface OnChangeListener extends BaseOnChangeListener<MiddleSlider> {
    @Override
    void onValueChange(@NonNull MiddleSlider slider, float value, boolean fromUser);
  }

  /**
   * Interface definition for callbacks invoked when a slider's touch event is being
   * started/stopped.
   */
  public interface OnSliderTouchListener extends BaseOnSliderTouchListener<MiddleSlider> {
    @Override
    void onStartTrackingTouch(@NonNull MiddleSlider slider);

    @Override
    void onStopTrackingTouch(@NonNull MiddleSlider slider);
  }

  public MiddleSlider(@NonNull Context context) {
    this(context, null);
  }

  public MiddleSlider(@NonNull Context context, @Nullable AttributeSet attrs) {
    this(context, attrs, R.attr.sliderStyle);
  }

  public MiddleSlider(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray a = context.obtainStyledAttributes(attrs, new int[] {android.R.attr.value});
    if (a.hasValue(0)) {
      setValue(a.getFloat(0, 0f));
    }
    valueActiveFixed = getValue();
    a.recycle();
  }

  /**
   * Returns the value of the slider.
   *
   * @see #setValue(float)
   * @attr ref com.google.android.material.R.styleable#Slider_android_value
   */
  public float getValue() {
    return getValues().get(0);
  }

  /**
   * Sets the value of the slider.
   *
   * <p>The thumb value must be greater or equal to {@code valueFrom}, and lesser or equal to {@code
   * valueTo}. If that is not the case, an {@link IllegalStateException} will be thrown when the
   * view is laid out.
   *
   * <p>If the slider is in discrete mode (i.e. the tick increment value is greater than 0), the
   * thumb's value must be set to a value falls on a tick (i.e.: {@code value == valueFrom + x *
   * stepSize}, where {@code x} is an integer equal to or greater than 0). If that is not the case,
   * an {@link IllegalStateException} will be thrown when the view is laid out.
   *
   * @param value The value to which to set the slider
   * @see #getValue()
   * @attr ref com.google.android.material.R.styleable#Slider_android_value
   */
  public void setValue(float value) {
    setValues(value);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setCustomThumbDrawable(@DrawableRes int drawableResId) {
    super.setCustomThumbDrawable(drawableResId);
  }

  /**
   * {@inheritDoc}
   */
  @Override
  public void setCustomThumbDrawable(@NonNull Drawable drawable) {
    super.setCustomThumbDrawable(drawable);
  }

  @Override
  protected boolean pickActiveThumb() {
    if (getActiveThumbIndex() != -1) {
      return true;
    }
    // Only one thumb to focus
    setActiveThumbIndex(0);
    return true;
  }

  @Override
  protected float[] getActiveRange() {
    if (valueActiveFixed <= getValueFrom()) {
      return super.getActiveRange();
    } else {
      List<Float> values = Arrays.asList(valueActiveFixed, getValue());
      float max = max(values);
      float min = min(values);
      float left = normalizeValue(min);
      float right = normalizeValue(max);
      // In RTL we draw things in reverse, so swap the left and right range values
      return isRtl() ? new float[]{right, left} : new float[]{left, right};
    }
  }
}
