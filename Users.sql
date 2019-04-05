/*
Navicat SQL Server Data Transfer

Source Server         : DBtest
Source Server Version : 140000
Source Host           : :1433
Source Database       : publisher
Source Schema         : dbo

Target Server Type    : SQL Server
Target Server Version : 140000
File Encoding         : 65001

Date: 2019-04-05 16:21:21
*/


-- ----------------------------
-- Table structure for Users
-- ----------------------------
DROP TABLE [dbo].[Users]
GO
CREATE TABLE [dbo].[Users] (
[ID] int NOT NULL IDENTITY(1,1) ,
[username] varchar(20) NULL ,
[Password] varchar(20) NULL ,
[fault_time] int NULL ,
[unlock_time] datetime NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[Users]', RESEED, 3)
GO

-- ----------------------------
-- Records of Users
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Users] ON
GO
INSERT INTO [dbo].[Users] ([ID], [username], [Password], [fault_time], [unlock_time]) VALUES (N'1', N'张三', N'123456', N'0', null)
GO
GO
INSERT INTO [dbo].[Users] ([ID], [username], [Password], [fault_time], [unlock_time]) VALUES (N'2', N'李四', N'123456', N'0', null)
GO
GO
INSERT INTO [dbo].[Users] ([ID], [username], [Password], [fault_time], [unlock_time]) VALUES (N'3', N'王五', N'123456', N'0', N'2019-03-28 14:20:32.000')
GO
GO
SET IDENTITY_INSERT [dbo].[Users] OFF
GO

-- ----------------------------
-- Indexes structure for table Users
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Users
-- ----------------------------
ALTER TABLE [dbo].[Users] ADD PRIMARY KEY ([ID])
GO
