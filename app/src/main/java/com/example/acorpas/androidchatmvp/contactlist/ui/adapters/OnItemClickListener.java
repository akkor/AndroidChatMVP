package com.example.acorpas.androidchatmvp.contactlist.ui.adapters;

import com.example.acorpas.androidchatmvp.entities.User;

/**
 * Created by a.corpas on 26/07/2016.
 */
public interface OnItemClickListener {
    void onItemClick(User user);
    void onItemLongClick(User user);
}
