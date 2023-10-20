import tkinter as tk

def calculate():
    try:
        result = eval(entry.get())
        entry.delete(0, tk.END)
        entry.insert(tk.END, str(result))
    except:
        entry.delete(0, tk.END)
        entry.insert(tk.END, "Invalid input")

def clear():
    entry.delete(0, tk.END)

window = tk.Tk()
window.title("Calculator")

entry = tk.Entry(window, width=20)
entry.grid(row=0, column=0, columnspan=4)

btns = [
    '7', '8', '9', '/',
    '4', '5', '6', '*',
    '1', '2', '3', '-',
    '0', '.', '=', '+',
    'C'
]

row_val = 1
col_val = 0

for button in btns:
    if button == "=":
        tk.Button(window, text=button, width=5, command=calculate).grid(row=row_val, column=col_val)
    elif button == "C":
        tk.Button(window, text=button, width=5, command=clear).grid(row=row_val, column=col_val)
    else:
        tk.Button(window, text=button, width=5, command=lambda button=button: entry.insert(tk.END, button)).grid(row=row_val, column=col_val)
    
    col_val += 1
    
    if col_val > 3:
        col_val = 0
        row_val += 1

window.mainloop()
