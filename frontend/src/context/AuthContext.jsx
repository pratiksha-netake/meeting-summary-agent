import { createContext, useContext, useEffect, useState } from "react";
const AuthContext = createContext();
export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);

    const [token, setToken] = useState(
        localStorage.getItem("token")
    );
    useEffect(()=>{
        const savedUser = localStorage.getItem("user");
        if(savedUser){
            setUser(JSON.parse(savedUser));
        }
    },[]);
    const login = (jwtToken, userData)=>{
    localStorage.setItem(
            "token",
            jwtToken
        );
        localStorage.setItem(
            "user",
            JSON.stringify(userData)
        );
        setToken(jwtToken);
        setUser(userData);
    };
    const logout = ()=>{
        localStorage.removeItem("token");
        localStorage.removeItem("user");
        setToken(null);
        setUser(null);
    };
    return (
        <AuthContext.Provider
            value={{
                user,
                token,
                login,
                logout,
                isAuthenticated: !!token
            }}
        >
            {children}
        </AuthContext.Provider>
    );
};
export const useAuth = ()=>{
    return useContext(AuthContext);
};