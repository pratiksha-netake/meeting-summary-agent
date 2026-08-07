import "../styles/global.css";


function Button({
    children,
    type = "button",
    onClick,
    disabled = false,
    className = ""
}) {


    return (

        <button

            type={type}

            onClick={onClick}

            disabled={disabled}

            className={`gradient-btn ${className}`}

        >

            {children}

        </button>

    );


}


export default Button;