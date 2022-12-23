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

/**
 *
 * @author <a href="mailto:robertvokac@nanoboot.org">Robert Vokac</a>
 * @since 0.1.0
 */
module octagon.plugin.person {
    exports org.nanoboot.octagon.plugin.person.classes;
    requires lombok;
    requires octagon.core;
    requires powerframework.json;
    requires powerframework.time;
    requires octagon.plugin.task;
    requires octagon.entity;
    requires org.mybatis;
    requires java.sql;
    requires octagon.plugin.api;
}
