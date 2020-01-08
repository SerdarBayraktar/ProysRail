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
 * Created by Proys Yazılım on 5.10.2019.
 */

public class L3_makine_expandableListView_adapter_kapali extends BaseExpandableListAdapter {
    private Context context;
    private List<String> listDataHeader;
    private List<String> sayiDataHeader;
    private List<String> listDataHeaderPuantaj;
    private HashMap<String,List<String>> listHashMap;
    private HashMap<String,List<String>> listHashMapPuantaj;

    @Override
    public int getGroupCount() {
        return listDataHeader.size();
    }
    public L3_makine_expandableListView_adapter_kapali(Context context, List<String> list,List<String> listPuantaj, HashMap<String, List<String>> listHashMap,HashMap<String, List<String>> listHashMapPuantaj,List<String> sayiDataHeader) {
        this.context = context;
        this.listDataHeader = list;
        this.listHashMap = listHashMap;
        this.listDataHeaderPuantaj = listPuantaj;
        this.listHashMapPuantaj = listHashMapPuantaj;
        this.sayiDataHeader = sayiDataHeader;
    }

    @Override
    public int getChildrenCount(int i) {
        return listHashMap.get(listDataHeader.get(i)).size();
    }

    @Override
    public Object getGroup(int i) {
        return listDataHeader.get(i);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return listHashMap.get(listDataHeader.get(groupPosition)).get(childPosition);
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
        String headerPuantaj = listDataHeaderPuantaj.get(groupPosition);
        String headerSayi = sayiDataHeader.get(groupPosition);

        if (convertView ==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.l3_makine_group_row_kapali,null);

        }
        TextView lblListHeader = convertView.findViewById(R.id.textView);
        TextView GroupPuantaj = convertView.findViewById(R.id.puantaj);
        TextView GroupSayi = convertView.findViewById(R.id.sayi);

        lblListHeader.setText(headerTitle);
        GroupPuantaj.setText(headerPuantaj);
        if (!headerSayi.equals("1")){
            GroupSayi.setText("("+String.valueOf(headerSayi)+")");
        }
        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition,childPosition);
        final String childpuantaj = listHashMapPuantaj.get(listDataHeader.get(groupPosition)).get(childPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.l3_isgucu_row_child_kapali,null);
        }
        TextView txtlistchild = convertView.findViewById(R.id.textView1);
        TextView txtpuantajchild =convertView.findViewById(R.id.puantaj);

        txtlistchild.setText(childText);
        txtpuantajchild.setText(childpuantaj);
        return convertView;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
