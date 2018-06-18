package james.alarmio.dialogs;

import android.content.Context;
import android.os.Bundle;

import com.afollestad.aesthetic.Aesthetic;

import io.reactivex.functions.Consumer;
import james.alarmio.utils.ColorUtils;
import me.jfenn.timedatepickers.dialogs.TimeSheetPickerDialog;

public class AestheticTimeSheetPickerDialog extends TimeSheetPickerDialog {

    public AestheticTimeSheetPickerDialog(Context context) {
        super(context);
    }

    public AestheticTimeSheetPickerDialog(Context context, int hourOfDay, int minute) {
        super(context, hourOfDay, minute);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Aesthetic.get()
                .textColorPrimary()
                .take(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        setPrimaryTextColor(integer);
                    }
                });

        Aesthetic.get()
                .textColorSecondary()
                .take(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        setSecondaryTextColor(integer);
                    }
                });

        Aesthetic.get().colorPrimary()
                .take(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        setBackgroundColor(integer);
                        setPrimaryBackgroundColor(integer);
                        setSecondaryBackgroundColor(integer);
                    }
                });

        Aesthetic.get().colorAccent()
                .take(1)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(Integer integer) {
                        setSelectionColor(integer);
                        setSelectionTextColor(ColorUtils.getPrimaryTextColor(getContext(), integer));
                    }
                });
    }
}
