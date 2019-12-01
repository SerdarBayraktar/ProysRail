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
public class L2_makine_expandable_listview_adapter extends BaseExpandableListAdapter {
    private Context context;
    private List<String>
            listDataHeader;
    private HashMap<String,List<String>> listHashMap;
    private HashMap<String,List<String>> listHashMapPuantaj;
    private HashMap<String,List<String>> listHashMapSayi;

    public L2_makine_expandable_listview_adapter(Context context, List<String> list, HashMap<String, List<String>> listHashMap,HashMap<String, List<String>> listHashMapPuantaj,HashMap<String, List<String>> listHashMapSayi) {
        this.context = context;
        this.listDataHeader = list;
        this.listHashMap = listHashMap;
        this.listHashMapPuantaj = listHashMapPuantaj;
        this.listHashMapSayi = listHashMapSayi;
    }
    @Override
    public int getGroupCount() {
        return listDataHeader.size();
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
        if (convertView ==null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.l2_is_gucu_group_row,null);
        }
        TextView lblListHeader = convertView.findViewById(R.id.imalat_adi);
        lblListHeader.setText(headerTitle);
        return convertView;

    }

    @Override
    public View getChildView(int groupPosition, int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        final String childText = (String) getChild(groupPosition,childPosition);
        final String childpuantaj = listHashMapPuantaj.get(listDataHeader.get(groupPosition)).get(childPosition);
        final String childsayi = listHashMapSayi.get(listDataHeader.get(groupPosition)).get(childPosition);
        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater)this.context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.l2_makine_child_row,null);
        }
        TextView txtlistchild = convertView.findViewById(R.id.isciadi);
        TextView txtpuantajchild =convertView.findViewById(R.id.calisilan_saat);
        TextView txtsayichild =convertView.findViewById(R.id.sayi);

        txtlistchild.setText(childText);
        txtpuantajchild.setText(childpuantaj);
        if (!childsayi.equals("1")){
            txtsayichild.setText("("+childsayi+")");
        }
        return convertView;
    }
    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
