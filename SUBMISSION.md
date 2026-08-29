# SUBMISSION - Exit Exam MVC 1/2569 (เสาร์บ่าย)

## 1. วิธีเปิดโปรแกรม
- ภาษา/เฟรมเวิร์ก: Java
- Entry point / คำสั่งเปิดโปรแกรม: รัน Main.java
- หมายเหตุที่จำเป็น (ถ้ามี): 

## 2. ตารางเชื่อมโยง Requirements

| Requirement | Model / Domain | Controller / Action | View / Screen |
|---|---|---|---|
| R1 | Members, Role_Change_Requests, Decisions, Role, RequestStatus, DecisionResult | DataLoader, MemberController, RequestController | ConsoleView |
| R2 | Members, Decision, Role_Change_Request | MemberController, RequestController | ConsoleView |
| R3 | Members, Decision, Role_Change_Request | RequestController | ConsoleView |
| R4 | Members, Role_Change_Requests | RequestController | ConsoleView |
| R5 | Role_Change_Requests | RequestController | ConsoleView |

## 3. ผลการทดสอบ

| กรณี | ผ่าน/ไม่ผ่าน | หมายเหตุ (เฉพาะที่จำเป็น) |
|---|---|---|
| T1 | ผ่าน | |
| T2 | ไม่ผ่าน | |
| T3 | ผ่าน | |
| T4 | ผ่าน | |
| T5 | ผ่าน | |
| T6 | ผ่าน | |

## 4. ความแตกต่างระหว่างแบบที่ออกกับโปรแกรมจริง (ถ้ามี)
ระบุไม่เกิน 3 ข้อ
1. เพิ่ม method / attribute
2. 
3. 

## 5. บันทึกการใช้ Generative AI
หากไม่ได้ใช้ ให้ระบุ **ไม่ได้ใช้ Generative AI**

| เวลาโดยประมาณ | เครื่องมือ | ใช้เพื่ออะไร | นำคำแนะนำไปใช้อย่างไร |
|---|---|---|---|
| 14.00 | Gemini AI | การใช้ Exception แบบต่างๆ | ช่วยเลือก Error exception |
| 15.30 | Gemini AI | Parse ไฟล์ seed_data.json | ช่วยเขียน Regex pattern , Matcher |
| | | | |
