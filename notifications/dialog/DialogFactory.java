package com.example.ngocsonit.smartclothing.notifications.dialog;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;

import com.example.ngocsonit.smartclothing.R;
import com.example.ngocsonit.smartclothing.activities.MainActivity;

/**
 * Created by ngocsonit on 20/03/2016.
 * Class UserDialodFactory : a factory show dialog and process user
 * input
 */
public class DialogFactory {

    // tag
    private static final String TAG = DialogFactory.class.getSimpleName();
    // constant index to indentify user actions
    private static final int IMAGE_FROM_CAMERA = 1;
    private static final int IMAGE_FROM_GALLERY = 0;


    private DialogFactory() {
        // prevent from being instanced
    }

    /**
     * Returns Builder corresponding to {@link DialogAction}
     *
     * @param dialogAction : {@link DialogAction} to identify which {@link Builder} to be turned
     * @return Builder corresponding to {@link DialogAction}
     */
    public static AlertDialog newInstance(Activity activity, DialogAction dialogAction) {
        AlertDialog alertDialog; /*Alert Dilog to be returned */
        // analyze the dialog ype
        switch (dialogAction) {
            case NEW_ITEM_CHOOSER:
                alertDialog = newItemChooserDialog(activity);
                break;
            default:
                alertDialog = null;
        }
        // return
        return alertDialog;
    }


    /**
     * Returns a {@link android.support.v7.app.AlertDialog.Builder} whose content is appropriated
     * to upload new image action
     *
     * @param activity : the {@link Activity} calling this method
     * @return a {@link android.support.v7.app.AlertDialog.Builder} whose content is appropriated
     * to upload new image action
     */
    private static AlertDialog newItemChooserDialog(final Activity activity) {
        android.support.v7.app.AlertDialog.Builder builder = new Builder(activity)
                .setIcon(R.drawable.ic_add_a_photo_black_36dp)
                .setTitle(R.string.new_item_chooser)
                .setItems(R.array.upload_types_choice, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // handle user input here
                        switch (which) {
                            case IMAGE_FROM_GALLERY:
                                // start a intent to pick up a images from gallery
                                Intent pickupImageIntent = new Intent(Intent.ACTION_GET_CONTENT);
                                pickupImageIntent.setType("image/*");
                                activity.startActivityForResult(pickupImageIntent, MainActivity.PICK_IMAGE_FROM_GALLERY_REQUEST_CODE);
                                break;
                            case IMAGE_FROM_CAMERA:
                                break;
                            default:
                                break;

                        }
                    }
                })
                .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // close the dialog
                        dialog.dismiss();
                    }
                });
        return builder.create();

    }


}
