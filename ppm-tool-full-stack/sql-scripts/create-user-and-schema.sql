CREATE USER 'ppmt'@'localhost' IDENTIFIED BY 'ppmt';

GRANT ALL PRIVILEGES ON ppmtcourse. * TO 'ppmt'@'localhost';

#
# Starting with MySQL 8.0.4, the MySQL team changed the
# default authentication plugin for MySQL server
# from mysql_native_password to caching_sha2_password.
#
# The command below will make the appropriate updates for your user account.
#
# See the MySQL Reference Manual for details:
# https://dev.mysql.com/doc/refman/8.0/en/caching-sha2-pluggable-authentication.html
#
ALTER USER 'ppmt'@'localhost' IDENTIFIED WITH mysql_native_password BY 'ppmt';




DROP SCHEMA IF EXISTS `ppmtcourse`;

CREATE SCHEMA `ppmtcourse`;

use `ppmtcourse`;