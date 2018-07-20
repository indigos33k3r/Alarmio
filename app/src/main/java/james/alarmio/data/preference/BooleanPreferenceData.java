package james.alarmio.data.preference;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.support.annotation.StringRes;
import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.TextView;

import io.multimoon.colorful.ColorfulKt;
import james.alarmio.R;
import james.alarmio.data.PreferenceData;

public class BooleanPreferenceData extends BasePreferenceData<BooleanPreferenceData.ViewHolder> {

    private PreferenceData preference;
    private int title;
    private int description;

    public BooleanPreferenceData(PreferenceData preference, @StringRes int title, @StringRes int description) {
        this.preference = preference;
        this.title = title;
        this.description = description;
    }

    @Override
    public BasePreferenceData.ViewHolder getViewHolder(LayoutInflater inflater, ViewGroup parent) {
        return new ViewHolder(inflater.inflate(R.layout.item_preference_boolean, parent, false));
    }

    @Override
    public void bindViewHolder(final ViewHolder holder) {
        holder.title.setText(title);
        holder.description.setText(description);
        holder.toggle.setOnCheckedChangeListener(null);

        Boolean value = preference.getValue(holder.itemView.getContext());
        holder.toggle.setChecked(value != null ? value : false);
        holder.toggle.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                preference.setValue(compoundButton.getContext(), b);
            }
        });

        int textColorPrimary = holder.getAlarmio().getTextColor();

        ColorStateList colorStateList = new ColorStateList(
                new int[][]{new int[]{-android.R.attr.state_checked}, new int[]{android.R.attr.state_checked}},
                new int[]{
                        Color.argb(100, Color.red(textColorPrimary), Color.green(textColorPrimary), Color.blue(textColorPrimary)),
                        ColorfulKt.Colorful().getAccentColor().getColorPack().normal().asInt()
                }
        );

        CompoundButtonCompat.setButtonTintList(holder.toggle, colorStateList);
        holder.toggle.setTextColor(textColorPrimary);
    }

    public class ViewHolder extends BasePreferenceData.ViewHolder {

        private TextView title;
        private TextView description;
        private SwitchCompat toggle;

        public ViewHolder(View v) {
            super(v);
            title = v.findViewById(R.id.title);
            description = v.findViewById(R.id.description);
            toggle = v.findViewById(R.id.toggle);
        }

    }

}
