package ode._infraestruturaBase.ciu;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.zkoss.zul.DefaultTreeNode;
import org.zkoss.zul.TreeNode;

@SuppressWarnings("rawtypes")
public class ImprovedTreeNode extends DefaultTreeNode {

		private static final long serialVersionUID = 1L;
		
		private List _children;
		
		@Override
		public String toString() {
			return super.getData().toString();
		}
		
		public ImprovedTreeNode(Object data) {
			super(data);
		}
		
		public ImprovedTreeNode(Object data, Collection children) {
			super(data, children);
		}

		public ImprovedTreeNode(Object data, ImprovedTreeNode[] children) {
			super(data, children);
		}

		public boolean isLeaf() {
			return _children == null || _children.size() == 0;
		}
		
		public void add(TreeNode child) {
			if (_children == null)
				_children = new ArrayList();
			super.add(child);
		}
		
		@SuppressWarnings("unchecked")
		public void insert(TreeNode child, int index) {
			if (_children == null)
				_children = new ArrayList();
			
			_children.add(index, child);
			super.insert(child, index);
		}
		
		public void remove(int index) {
			super.remove(index);
			_children.remove(index);
		}
		//@Override
		public void remove(TreeNode child) {
			super.remove(child);
			_children.remove(child);
		}

	}