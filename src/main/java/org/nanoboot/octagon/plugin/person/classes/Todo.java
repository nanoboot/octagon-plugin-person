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

import org.nanoboot.octagon.plugin.task.ReminderType;
import org.nanoboot.powerframework.time.moment.LocalDate;
import org.nanoboot.octagon.entity.core.Entity;
import org.nanoboot.octagon.entity.core.EntityAttribute;
import org.nanoboot.octagon.entity.core.EntityAttributeType;
import lombok.Data;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.1.0
 */
@Data
public class Todo implements Entity {
    /**
     * UUID identification of this entity.
     */
    private UUID id;
    private String name;
    private LocalDate deadline;
    private LocalDate lastReminded;
    private ReminderType reminderType;
    private String note;
    private Boolean done;

    public void validate() {

    }

    @Override
    public void loadFromMap(Map<String, String> map) {
        setName(getStringParam("name", map));
        setDeadline(getLocalDateParam("deadline", map));
        setLastReminded(getLocalDateParam("lastReminded", map));

        String reminderTypeFromRequest = getStringParam("reminderType", map);
        setReminderType(reminderTypeFromRequest == null ? null : ReminderType.valueOf(reminderTypeFromRequest));
        setNote(getStringParam("note", map));
        setDone(getBooleanParam("done", map));
    }

    @Override
    public Class getEntityClass() {
        return getClass();
    }

    @Override
    public String[] toStringArray() {
        return new String[]{
                id== null ? "" : id.toString(),
                name == null ? "" : name,
                deadline == null ? "" : deadline.toString(),
                lastReminded == null ? "" : lastReminded.toString(),
                reminderType == null ? "" : reminderType.name(),
                note == null ? "" : note,
                done == null ? "" : convertBooleanToString(done)
        };
    }

    @Override
    public List<EntityAttribute> getSchema() {
        List<EntityAttribute> list = new ArrayList<>();

        list.add(EntityAttribute.getIdEntityAttribute());
        list.add(new EntityAttribute("name").withMandatory(true));
        list.add(new EntityAttribute("deadline", EntityAttributeType.LOCAL_DATE));
        list.add(new EntityAttribute("lastReminded", EntityAttributeType.LOCAL_DATE));
        list.add(new EntityAttribute("reminderType", Arrays.asList(ReminderType.values()).stream().map(ReminderType::name).collect(Collectors.toList())));
        list.add(new EntityAttribute("note"));
        list.add(new EntityAttribute("done", EntityAttributeType.BOOLEAN));

        return list;
    }

}
