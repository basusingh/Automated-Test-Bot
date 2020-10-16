import tkinter as tk
from tkinter import filedialog,ttk
import requests
from jnius import autoclass

class Root(tk.Tk):

    def __init__(self, *args, **kwargs):
        
        tk.Tk.__init__(self, *args, **kwargs)
        container = tk.Frame(self,bg='red')
        #variable declaration
        self.geometry("500x300")
        self.title("SOundCloud")
        container.pack(side="top", fill="both", expand = True)
        container.grid_rowconfigure(0, weight=1)
        container.grid_columnconfigure(0, weight=1)
        self.frames = {}
        for F in (page1,):
            frame = F(container, self)
            self.frames[F] = frame
            frame.grid(row=0, column=0, sticky="nsew")
        self.show_frame(page1)
    #meathod to call any frame
    def show_frame(self, cont):
        frame = self.frames[cont]
        frame.tkraise()
    #meathod to call any page variable
    def getpage(self,page):
        return self.frames[page]

class page1(tk.Frame):
    def __init__(self, parent, controller):
        tk.Frame.__init__(self,parent)
        self.controller=controller
        
        self.threadsframe = tk.LabelFrame(text="Threads")
        self.threads = tk.Entry(self.threadsframe)
        self.threadsframe.place(x=0,y=0)
        self.threads.pack()
        self.proxiesframe = tk.LabelFrame(text="Proxies")
        self.proxiestypevar = tk.StringVar()
        self.proxiesformatvar=tk.StringVar()
        self.proxiesformatlabelframe = tk.LabelFrame(self.proxiesframe, text = "Proxies format")
        self.proxiestypelabelframe = tk.LabelFrame(self.proxiesframe, text ="Proxies Type")

        self.proxieslabel = tk.Entry(self.proxiesframe)
        self.browsebutton = tk.Button(self.proxiesframe,text="browse", command= lambda : self.proxiesbrowse())
        self.proxiesformat = ttk.Combobox(self.proxiesformatlabelframe,textvariable=self.proxiesformatvar)
        self.proxiesformat['values']=('ip:port','ip:port:user:pass')
        self.proxiestype = ttk.Combobox(self.proxiestypelabelframe, textvariable=self.proxiestypevar)
        self.proxiestype['values'] = ('http','sock4','sock5')

        self.proxiesframe.place(x=0,y=50)
        self.proxieslabel.pack()
        self.browsebutton.pack()
        self.proxiestype.pack()
        self.proxiesformat.pack()
        self.proxiesformatlabelframe.pack()
        self.proxiestypelabelframe.pack()

        self.accountsframe =tk.LabelFrame(text="Accounts")
        self.accountsframe.place(x=0,y=200)
        self.acclabel = tk.Entry(self.accountsframe)
        self.acclabel.pack()
        self.accbrowsebut = tk.Button(self.accountsframe, text = "browse" , command = lambda : self.accbrowse())
        self.accbrowsebut.pack()

        self.threaddelayvar = tk.StringVar()
        self.threadelaylabelframe = tk.LabelFrame(self, text = "thread delay (seconds)")
        self.threaddelay = ttk.Combobox(self.threadelaylabelframe,textvariable = self.threaddelayvar, values = (1,2,3,4,5,6,7,8,9,10))
        self.threadelaylabelframe.pack()
        self.threaddelay.pack()

        self.playtimeminvar = tk.IntVar()
        self.playtimemaxvar = tk.IntVar()
        self.playtimemaxlabelframe = tk.LabelFrame(self, text= "max playtime")
        self.playtimeminlabelframe = tk.LabelFrame(self, text= "min playtime")
        self.playtimemin = tk.Entry(self.playtimeminlabelframe, textvariable=self.playtimeminvar)
        self.playtimemax = tk.Entry(self.playtimemaxlabelframe, textvariable = self.playtimemaxvar)
        self.playtimemaxlabelframe.pack()
        self.playtimeminlabelframe.pack()
        self.playtimemax.pack()
        self.playtimemin.pack()

        self.startbut = tk.Button(self,text="GO!!", command = lambda : self.run())
        self.startbut.pack()

    def proxiesbrowse(self):
        self.proxiesfilename=filedialog.askopenfilename(initialdir="/", title="proxies",filetype=[("text",".txt")])
        self.proxieslabel.insert(0,self.proxiesfilename)

    def accbrowse(self):
        self.accfilename=filedialog.askopenfilename(initialdir="/", title="accounts",filetype=[("text",".txt")])
        self.acclabel.insert(0,self.accfilename)

    def run(self):
	//Add to DB
        print("threads:",int(self.threads.get()))
        print("proxies file location:",str(self.proxieslabel.get()))
        print("proxies format:",str(self.proxiesformatvar.get()))
        print("proxies type:",str(self.proxiestypevar.get()))
        print("acc file location:",str(self.acclabel.get()))
        print("thread delay:",str(self.threaddelayvar.get()))
        print("playtime min:",str(self.playtimeminvar.get()))
        print("playtime max:",str(self.playtimemax.get()))
        
    def stopHub(hub_port):
    	r =requests.get('http://', hub_host, ':' + hub_port, '/lifecycle-manager?action=shutdown')
    	print('Status Code: ', r.status_code)
    
    #Download https://github.com/kivy/pyjnius
    def callJava1():
    	Stream = autoclass('com.basusingh.scbot.StreamMain')
    	stream = Stream()
    	stream.setUpHubPort(hubHost, hubPort, nodeHost, nodePort, self.threads.get())
        

root = Root()
root.bind("<F11>", lambda event: root.attributes("-fullscreen",
                                    not root.attributes("-fullscreen")))
root.bind("<Escape>", lambda event: root.attributes("-fullscreen", False))
root.mainloop()


