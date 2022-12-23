///////////////////////////////////////////////////////////////////////////////////////////////
// Octagon Plugin Person: Person plugin for Octagon application.
// Copyright (C) 2021-2022 the original author or authors.
//
// This program is free software; you can redistribute it and/or
// modify it under the terms of the GNU General Public License
// as published by the Free Software Foundation; version 2
// of the License only.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
///////////////////////////////////////////////////////////////////////////////////////////////

package org.nanoboot.octagon.plugin.person.classes;

import org.nanoboot.octagon.entity.core.EntityAttribute;
import org.nanoboot.octagon.plugin.task.AbstractTask;

import java.util.List;

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.1.0
 */
public class PersonTask extends AbstractTask {
    private static List<EntityAttribute> SCHEMA;

    @Override
    public String getOldEntityClass() {
        return "RvTask";
    }
    @Override
    public void validate() {
        super.validate();
    }

    @Override
    public Class getEntityClass() {
        return getClass();
    }

    @Override
    public List<EntityAttribute> getSchema() {
        if (SCHEMA == null) {
            SCHEMA = EntityAttribute.copy(super.createAbstractSchema());
            for (EntityAttribute e : SCHEMA) {
                if (e.getName().equals("parentId")) {
                    String fKName = getForeignKeyNameForParent();
                    e.setCustomHumanName("Parent RV task"/*String.valueOf(fKName.charAt(0)).toUpperCase() + fKName.substring(1)*/);
                    e.setForeignKey(fKName);
                    e.setNamedList("getPersonTasks");
                    e.setNamedListArgPropertyName("id");
                    break;
                }
            }
        }
        return SCHEMA;
    }

    String getForeignKeyNameForParent() {
        return "personTask";
    }

    @Override
    public String[] getRelatedListsForEntity() {
        return new String[]{
                "getSubPersonTasks"
        };
    }

    public String[] getRelatedActionsForEntity() {
        return new String[]{
                "Add Sub RV Task:create?className=PersonTask&parentId=",
                "List sub RV tasks:list?className=PersonTask&filter_parentId=",
        };
    }
}
