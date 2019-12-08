package net.proys.proysrail;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.TextView;

import java.util.HashMap;
import java.util.List;

/**
 * Created by Proys Yazılım on 8.12.2019.
 */

public class L2_aciklama_Expandable_Listview_adapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String> imalatlar_isim;
    private HashMap<String,List<String>> listAciklamalar;

    public L2_aciklama_Expandable_Listview_adapter(Context context, List<String> imalatlar_isim, HashMap<String, List<String>> listAciklamalar) {
        this.context = context;
        this.imalatlar_isim = imalatlar_isim;
        this.listAciklamalar = listAciklamalar;
    }
    @Override
    public int getGroupCount() {
        return imalatlar_isim.size();
    }

    @Override
    public int getChildrenCount(int i) {
        return listAciklamalar.get(imalatlar_isim.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return imalatlar_isim.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listAciklamalar.get(imalatlar_isim.get(groupPosition)).get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        String headerTitle = (String) getGroup(groupPosition);
        if (convertView ==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.l2_aciklama_group_row,null);
        }
        TextView lblListHeader = convertView.findViewById(R.id.imalat_adi);
        lblListHeader.setText(headerTitle);
        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition,childPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.l2_aciklama_child_row,null);
        }
        TextView txtlistchild = convertView.findViewById(R.id.aciklama_txt);
        txtlistchild.setText(childText);
        return convertView;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
