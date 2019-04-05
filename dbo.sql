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

Date: 2019-04-05 16:09:05
*/


-- ----------------------------
-- Table structure for Argments
-- ----------------------------
DROP TABLE [dbo].[Argments]
GO
CREATE TABLE [dbo].[Argments] (
[ID] int NOT NULL IDENTITY(1,1) ,
[num] int NULL ,
[days] int NULL ,
[lock_days] int NULL 
)


GO

-- ----------------------------
-- Records of Argments
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Argments] ON
GO
INSERT INTO [dbo].[Argments] ([ID], [num], [days], [lock_days]) VALUES (N'1', N'4', N'4', N'2')
GO
GO
SET IDENTITY_INSERT [dbo].[Argments] OFF
GO

-- ----------------------------
-- Table structure for Rights
-- ----------------------------
DROP TABLE [dbo].[Rights]
GO
CREATE TABLE [dbo].[Rights] (
[id] int NOT NULL IDENTITY(1,1) ,
[rightNO] int NULL ,
[rightName] varchar(20) NULL ,
[Module] varchar(20) NULL ,
[W_name] varchar(200) NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[Rights]', RESEED, 9)
GO

-- ----------------------------
-- Records of Rights
-- ----------------------------
SET IDENTITY_INSERT [dbo].[Rights] ON
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'1', N'1001', N'选题管理', N'编务管理', N'W_xtgl')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'2', N'1002', N'报批查询', N'编务管理', N'W_bpcx')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'3', N'1003', N'组稿查询', N'编务管理', N'W_zgcx')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'4', N'2001', N'图书定价', N'出版管理', N'W_tsdj')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'5', N'2002', N'计划发排', N'出版管理', N'W_jhfp')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'6', N'2003', N'印刷装订', N'出版管理', N'W_yszd')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'7', N'2004', N'结算处理', N'出版管理', N'W_jscl')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'8', N'3001', N'新书入库', N'储运管理', N'W_xsrk')
GO
GO
INSERT INTO [dbo].[Rights] ([id], [rightNO], [rightName], [Module], [W_name]) VALUES (N'9', N'3002', N'盘点入库', N'储运管理', N'W_pdrk')
GO
GO
SET IDENTITY_INSERT [dbo].[Rights] OFF
GO

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
-- Table structure for UsersRights
-- ----------------------------
DROP TABLE [dbo].[UsersRights]
GO
CREATE TABLE [dbo].[UsersRights] (
[ID] int NOT NULL IDENTITY(1,1) ,
[userID] int NULL ,
[rightID] int NULL 
)


GO
DBCC CHECKIDENT(N'[dbo].[UsersRights]', RESEED, 11)
GO

-- ----------------------------
-- Records of UsersRights
-- ----------------------------
SET IDENTITY_INSERT [dbo].[UsersRights] ON
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'1', N'1', N'1')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'2', N'1', N'2')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'3', N'1', N'3')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'4', N'2', N'1')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'5', N'2', N'4')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'6', N'3', N'2')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'7', N'3', N'6')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'8', N'3', N'7')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'9', N'1', N'6')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'10', N'1', N'7')
GO
GO
INSERT INTO [dbo].[UsersRights] ([ID], [userID], [rightID]) VALUES (N'11', N'1', N'8')
GO
GO
SET IDENTITY_INSERT [dbo].[UsersRights] OFF
GO

-- ----------------------------
-- View structure for UsersUNRights
-- ----------------------------
DROP VIEW [dbo].[UsersUNRights]
GO
CREATE VIEW [dbo].[UsersUNRights] AS 
SELECT Users.ID 'userid', Rights.id 'rightid'
FROM Users CROSS JOIN Rights
WHERE (Rights.id NOT in
       (SELECT rightID
         FROM UsersRights
          WHERE UsersRights.userID=Users.ID))
GO

-- ----------------------------
-- Indexes structure for table Argments
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Argments
-- ----------------------------
ALTER TABLE [dbo].[Argments] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table Rights
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Rights
-- ----------------------------
ALTER TABLE [dbo].[Rights] ADD PRIMARY KEY ([id])
GO

-- ----------------------------
-- Indexes structure for table Users
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table Users
-- ----------------------------
ALTER TABLE [dbo].[Users] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Indexes structure for table UsersRights
-- ----------------------------

-- ----------------------------
-- Primary Key structure for table UsersRights
-- ----------------------------
ALTER TABLE [dbo].[UsersRights] ADD PRIMARY KEY ([ID])
GO

-- ----------------------------
-- Foreign Key structure for table [dbo].[UsersRights]
-- ----------------------------
ALTER TABLE [dbo].[UsersRights] ADD FOREIGN KEY ([rightID]) REFERENCES [dbo].[Rights] ([id]) ON DELETE CASCADE ON UPDATE CASCADE
GO
ALTER TABLE [dbo].[UsersRights] ADD FOREIGN KEY ([userID]) REFERENCES [dbo].[Users] ([ID]) ON DELETE CASCADE ON UPDATE CASCADE
GO
