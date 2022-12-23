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

import org.nanoboot.powerframework.time.moment.LocalDate;
import org.nanoboot.octagon.core.exceptions.OctagonException;
import org.nanoboot.octagon.entity.core.Entity;
import org.nanoboot.octagon.entity.core.EntityAttribute;
import org.nanoboot.octagon.entity.core.EntityAttributeType;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.1.0
 */
@Data
public class Thing implements Entity {
    /**
     * UUID identification of this entity.
     */
    private UUID id;
    private String path1;
    private String path2;
    private String path3;
    private String path4;
    private String name;
    private String alias;
    private LocalDate since;
    private String priceKc;
    private String note;

    @Override
    public void validate() {
        if (priceKc != null) {
            try {
                Double.valueOf(priceKc);
            } catch (Exception e) {
                throw new OctagonException("priceKc must be a double: " + e);
            }
        }

    }

    public String getPath() {
        StringBuilder sb = new StringBuilder();
        if (path1 != null) {
            sb.append(path1);
        }
        String SEPARATOR = ">";
        if (path2 != null) {
            sb.append(SEPARATOR);
            sb.append(path2);
        }
        if (path3 != null) {
            sb.append(SEPARATOR);
            sb.append(path3);
        }
        if (path4 != null) {
            sb.append(SEPARATOR);
            sb.append(path4);
        }
        return sb.toString();
    }

    @Override
    public void loadFromMap(Map<String, String> map) {
        setPath1(getStringParam("path1", map));
        setPath2(getStringParam("path2", map));
        setPath3(getStringParam("path3", map));
        setPath4(getStringParam("path4", map));
        setName(getStringParam("name", map));
        setAlias(getStringParam("alias", map));
        setSince(getLocalDateParam("since", map));
        setPriceKc(getStringParam("priceKc", map));
        setNote(getStringParam("note", map));
    }

    @Override
    public Class getEntityClass() {
        return getClass();
    }
    @Override
    public String[] toStringArray() {
        return new String[]{
                id == null ? "" : id.toString(),
                path1 == null ? "" : path1,
                path2 == null ? "" : path2,
                path3 == null ? "" : path3,
                path4 == null ? "" : path4,
                name == null ? "" : name,
                alias == null ? "" : alias,
                since == null ? "" : since.toString(),
                priceKc == null ? "" : priceKc,
                note == null ? "" : note
        };
    }

    @Override
    public List<EntityAttribute> getSchema() {
        List<EntityAttribute> list = new ArrayList<>();

        list.add(EntityAttribute.getIdEntityAttribute());
        list.add(new EntityAttribute("path1").withCustomHumanName("Path 1"));
        list.add(new EntityAttribute("path2").withCustomHumanName("Path 2"));
        list.add(new EntityAttribute("path3").withCustomHumanName("Path 3"));
        list.add(new EntityAttribute("path4").withCustomHumanName("Path 4"));
        list.add(new EntityAttribute("name").withMandatory(true));
        list.add(new EntityAttribute("alias"));
        list.add(new EntityAttribute("since", EntityAttributeType.LOCAL_DATE));
        list.add(new EntityAttribute("priceKc").withCustomHumanName("Price Kč"));
        list.add(new EntityAttribute("note"));

        return list;
    }

    public String[] getRelatedActionsForEntity() {
        return new String[]{
                "Start reading:create?className=ReadingDiary&thingId=",
        };
    }
}
